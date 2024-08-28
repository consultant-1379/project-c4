<template>

  <nav aria-label="main navigation" class="navbar is-info" role="navigation">
    <div class="navbar-brand">
      <a class="navbar-item" href="#">
        <img alt="ENM Test Analysis Tool" src="../assets/ECON_RGB_96px.png">
      </a>
    </div>
    <div id="navbarBasicExample" class="navbar-menu">
      <div class="navbar-brand">
        <a class="navbar-item main-title" href="#">
          ENM Test Analysis Tool
        </a>

        <a aria-expanded="false" aria-label="menu" class="navbar-burger" data-target="navbarBasicExample" role="button">
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
        </a>
      </div>

      <div class="navbar-end">
        <div class="navbar-item">
          <div class="buttons">
            <a class="button" @click="addProject()">
              ADD PROJECT
            </a>
          </div>
        </div>
      </div>
    </div>
  </nav>

</template>

<script>

import router from "@/router";

export default {
  programmeName: "Search",
  data() {
    return {
      addProjectModal: false,
      addingProject: false,
      productDataMissing: false,
      programme: {
        programmeNumber: "",
        programmeName: "",
        productList: []
      },
      product: {
        productNumber: "",
        productName: "",
        cnaList: []
      },
      cna: {
        cnaNumber: "",
        cnaName: "",
        cxpList: []
      },
      cxp: {
        cxpNumber: "",
        cxpName: "",
        cxpRepo: "",
        jenkinsJobURL: "",
      },
    }
  },
  methods: {
    addProject() {
      router.push('add-project');
    }
  }
}

</script>

<style scoped>

.modal-section p {
  padding-left: 4px;
  font-size: 16px;
  font-weight: bold;
}

.main-title {
  font-size: x-large;
  font-weight: bold;
  letter-spacing: 1px;
}

* {
  font-family: "Ericsson Hilda", serif;
}

</style>
