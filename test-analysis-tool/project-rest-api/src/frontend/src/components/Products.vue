<template>

  <main className="main-area">
    <div className="centered">
      <section className="cards">
        <ProjectCard v-for="project in projects" :key="project" :parentData="project"/>
      </section>
    </div>
  </main>

</template>

<script>

import ProjectCard from "@/components/ProductCard";
import DataService from "@/services/DataService";

export default {
  programmeName: "Products",
  components: {ProjectCard},
  data() {
    return {
      projects: [],
      test: 0
    }
  },
  mounted() {
    DataService.getAllProjects()
        .then((response) => {
          this.projects = response.data;
          console.info("Product:", response.data);
        });
  }
}

</script>

<style scoped>

@media screen and (min-width: 40em) {
  .cards {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }
}

* {
  font-family: "Ericsson Hilda", serif;
}

</style>