<template>

  <nav aria-label="main navigation" class="navbar is-info" role="navigation">
    <div class="navbar-brand">
      <a class="navbar-item" href="#">
        <img alt="ENM Test Analysis Tool" src="../assets/ECON_RGB_96px.png">
      </a>
    </div>
    <div id="navbarBasicExample" class="navbar-menu">
      <div class="navbar-start">
        <a class="navbar-item text-style">
          {{ parentData.number }}
        </a>

        <a class="navbar-item text-style">
          {{ parentData.name }}
        </a>
      </div>

      <div class="navbar-end">
        <div class="navbar-item">
          <div class="buttons">
            <a class="button is-danger" @click="goHome()">
              CLOSE
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
  name: "NavBar",
  props: ['parentData'],
  methods: {
    goHome() {
      router.push('/');
    }
  }
}

</script>

<style scoped>

.text-style {
  font-size: 20px;
  letter-spacing: .06rem;
  font-weight: bold;
}

* {
  font-family: "Ericsson Hilda", serif;
}

</style>