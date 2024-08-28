<template>
  <Header/>
  <Projects/>
</template>

<script>

import Projects from '@/components/Products.vue'
import Header from "@/components/Search";

export default {
  programmeName: 'Home',
  components: {
    Header,
    Projects
  }
}

</script>

<style>

/*Bulma CSS for styling the Frontend*/
@import "~bulma/css/bulma.css";

</style>