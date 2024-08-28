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
            <a class="button">
              ADD PROJECT
            </a>
          </div>
        </div>
      </div>
    </div>
  </nav>

  <div class="modal is-active">
    <div class="modal-background" v-on:click="popRouteStack()"></div>
    <div class="modal-card">
      <header class="modal-card-head">
        <p class="modal-card-title">Add Project</p>
        <button aria-label="close" class="delete" v-on:click="popRouteStack()"></button>
      </header>
      <section class="modal-card-body ">
        <div class="box small-box">
          <div class="modal-heading">Programme</div>
          <div>
            <input v-model="programme.programmeNumber" class="input cna-number" placeholder="Programme Number"
                   type="text">
            <input v-model="programme.programmeName" class="input cna-name" placeholder="Programme Name" type="text">
          </div>
          <br>
        </div>
        <div class="box small-box">
          <div class="modal-heading">Product</div>
          <div>
            <input v-model="product.productNumber" class="input cna-number" placeholder="Product Number" type="text">
            <input v-model="product.productName" class="input cna-name" placeholder="Product Name" type="text">
          </div>
          <br>
        </div>
        <div class="box small-box">
          <div class="modal-heading">CNA</div>
          <div>
            <input v-model="cna.cnaNumber" class="input cna-number" placeholder="CNA Number" type="text">
            <input v-model="cna.cnaName" class="input cna-name" placeholder="CNA Name" type="text">
          </div>
          <br>
        </div>
        <div class="box big-box">
          <div class="modal-heading">CXP</div>
          <div>
            <input v-model="cxp.cxpNumber" class="input cna-number" placeholder="CXP Number" type="text">
            <input v-model="cxp.cxpName" class="input cna-name" placeholder="CXP Name" type="text">
          </div>
          <div class="repo-jenkins-inputs">
            <input v-model="cxp.cxpRepo" class="input cna-number" placeholder="CXP Repo" type="text">
            <input v-model="cxp.jenkinsJobURL" class="input cna-name" placeholder="Jenkins Job URL" type="text">
          </div>
        </div>

        <p v-if="productDataMissing" class="help is-danger">
          Please fill in the missing fields!
        </p>

      </section>
      <footer class="modal-card-foot field is-grouped is-grouped-right">
        <button class="button is-info" type="submit" v-bind:class="{ 'is-loading': addingProject }"
                v-on:click="addNewProject()">
          Add
          Project
        </button>
        <button class="button is-danger" v-on:click="popRouteStack()">Cancel</button>
      </footer>
    </div>
  </div>

  <template>
    <button ref="button1" @click="toast"></button>
  </template>

</template>

<script>

import DataService from "@/services/DataService";
import router from "@/router";
import {createToast} from "mosha-vue-toastify";

export default {
  name: "AddProject",
  setup() {
    const toast = () => {
      createToast({
            title: 'Project Added Successfully!',
          },
          {
            position: 'bottom-right',
            showIcon: true,
            timeout: 5000,
            type: 'success',
            transition: 'slide'
          })
    }
    return {toast}
  },
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
    popRouteStack() {
      router.go(-1);
    },
    checkProjectDetails() {
      let projectFormFilled = true;
      if (this.programme.programmeNumber === "" || this.programme.programmeName === ""
          || this.product.productNumber === "" || this.product.productName === ""
          || this.cna.cnaNumber === "" || this.cna.cnaName === ""
          || this.cxp.cxpNumber === "" || this.cxp.cxpName === ""
          || this.cxp.cxpRepo === "" || this.cxp.jenkinsJobURL === "") {
        this.productDataMissing = true;
        projectFormFilled = false;
      }
      return projectFormFilled;
    },

    addNewProject() {
      this.cna.cxpList.push(this.cxp);
      this.product.cnaList.push(this.cna);
      this.programme.productList.push(this.product);

      if (this.checkProjectDetails()) {
        this.addingProject = true;
        DataService.addProject(JSON.stringify(this.programme))
            .then(response => {
              console.info("Add Project:", response.data);
              this.addingProject = false;
              this.popRouteStack();
              this.$refs.button1.click();
            })
            .catch(e => {
              console.error(e);
              this.addingProject = false;
              this.addProjectModal = !this.addProjectModal;
            });
      }
    }
  }
}

</script>

<style scoped>

.field {
  padding-top: 20px;
}

.input {
  width: 700px;
}

.modal-section p {
  padding-left: 4px;
  font-size: 16px;
  font-weight: bold;
}

.modal-heading {
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  padding-bottom: 8px;
}

.cna-number {
  width: 48%;
  float: left;
}

.cna-name {
  text-align: center;
  width: 48%;
  float: right;
}

.small-box {
  padding-bottom: 38px;
}

.big-box {
  padding-bottom: 62px;
}

.repo-jenkins-inputs {
  margin-top: 50px;
}

* {
  font-family: "Ericsson Hilda", serif;
}

</style>