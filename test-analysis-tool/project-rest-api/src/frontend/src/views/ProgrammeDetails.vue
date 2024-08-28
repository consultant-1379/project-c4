<template>

  <NavBar :parentData="navDetails"/>
  <div class="cxp-title">
    Products
  </div>
  <div class="box cxps">
    <div class="container">
      <section class="cards">
        <ProductList v-for="product in programme.productList" :key="product" :parentData="product"/>
      </section>
    </div>
  </div>
  <div class="cxp-title">
    Test Report
  </div>
  <TestReport :testReports="testReports"/>

  <div v-if="highestFailWait" class="cxp-title">
    Highest Failing Test
  </div>

  <HighestFailingTest v-if="highestFailWait" :highestTestFail="testReports.highestTestFail"/>

  <template>
    <button ref="button1" @click="toast"></button>
  </template>

</template>

<script>

import TestReport from "@/components/TestReport";
import NavBar from "@/components/NavBar";
import ProductList from "@/components/ProductList";
import DataService from "@/services/DataService";
import HighestFailingTest from "@/components/HighestFailingTest";
import {createToast} from 'mosha-vue-toastify';
import 'mosha-vue-toastify/dist/style.css'

export default {
  props: ['parentData'],
  name: "ProgrammeDetails",
  setup() {
    const toast = () => {
      createToast({
            title: 'Attention!',
            description: 'This Project has not been tested in over 7 days!'
          },
          {
            position: 'bottom-right',
            showIcon: true,
            timeout: 5000,
            type: 'danger',
            transition: 'slide'
          })
    }
    return {toast}
  },
  data() {
    return {
      programme: JSON.parse(this.parentData),
      navDetails: {},
      testReportData: {
        jobName: "",
        jenkinsUrl: "",
        jobNames: []
      },
      testReports: {
        reportName: "",
        totalTests: 0,
        totalPasses: 0,
        totalFails: 0,
        totalSkips: 0
      },
      dummyTest: {
        reportName: "Dummy Test Report",
        totalTests: 30,
        totalPasses: 20,
        totalFails: 5,
        totalSkips: 5,
      },
      dateDiff: false,
      highestFailWait: false
    }
  },
  components: {HighestFailingTest, ProductList, TestReport, NavBar},
  mounted() {
    this.setNavDetails();

    console.log("Programme Details:", this.programme);

    this.testReportData.jobName = this.programme.programmeName;
    this.testReportData.jenkinsUrl = this.programme.productList[0].cnaList[0].cxpList[0].jenkinsJobURL;

    for (let products of this.programme.productList) {
      for (let cnas of products.cnaList) {
        for (let cxps of cnas.cxpList) {
          this.testReportData.jenkinsUrl = cxps.jenkinsJobURL.split('/')[0].concat('//').concat(cxps.jenkinsJobURL.split('/')[2]).concat('/');
          this.testReportData.jobNames.push(cxps.jenkinsJobURL.split('/')[3]);
        }
      }
    }

    DataService.getAllReportsForProjectENM(this.testReportData)
        .then((response) => {
          this.testReports = response.data !== null ? response.data : this.dummyTest;
          setTimeout(() => this.highestFailWait = this.testReports.highestTestFail.fails > 0, 1);
          this.getTestDateDiff();
        });
  },
  methods: {
    setNavDetails() {
      this.navDetails.number = this.programme.programmeNumber;
      this.navDetails.name = this.programme.programmeName;
    },
    getTestDateDiff() {
      let date = new Date(this.testReports.lastTestTime);
      let today = new Date();

      const diffTime = Math.abs(today - date);
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

      if (0 < diffDays >= 7)
        this.$refs.button1.click();
    }
  }
}

</script>

<style scoped>

.container {
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-start;
  align-items: flex-start;
  margin-left: 0;
  overflow-scrolling: inherit;
}

.cards {
  padding: 1px;
}

.cxps {
  margin: 12px;
  padding: 0;
}

.cxp-title {
  font-size: 34px;
  text-align: left;
  margin: 18px 0 -10px 18px;
  font-weight: bold;
}

* {
  font-family: "Ericsson Hilda", serif;
}

</style>