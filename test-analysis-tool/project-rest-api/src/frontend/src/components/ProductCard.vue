<template>

  <div class="box" @click="programmeDetails()">
    <article class="media">
      <div class="media-content">
        <div class="content">
          <div>
            <h4>
              {{ parentData.programmeNumber }}
            </h4>
            <h6>
              {{ parentData.programmeName }}
            </h6>
          </div>
          <div class="projectDetails">
            <p>
              Product:
              {{ parentData.productList.length }}
            </p>
            <p>
              CNA:
              {{ parentData.productList[0].cnaList.length }}
            </p>
            <p>
              CXP:
              {{ parentData.productList[0].cnaList[0].cxpList.length }}
            </p>
          </div>
        </div>
      </div>
    </article>
  </div>

</template>

<script>

import router from "@/router";

export default {
  programmeName: "ProductCard",
  props: ['parentData'],
  methods: {
    programmeDetails() {
      router.push({name: 'ProgrammeDetails', params: {parentData: JSON.stringify(this.parentData)}});
    }
  }
}

</script>

<style scoped>

@media screen and (min-width: 60em) {
  .box {
    flex: 0 1 calc(25% - 1em);
  }
}

.box {
  margin: 16px;
  max-width: 375px;
  max-height: 500px;
  overflow: hidden;
  cursor: pointer;
}

.box a {
  color: black;
  text-decoration: none;
}

.box a:hover {
  box-shadow: 3px 3px 8px hsl(0, 0%, 80%);
}

.box-content h2 {
  margin-top: 0;
  margin-bottom: .5em;
  font-weight: bold;
}

.box-content p {
  font-size: 80%;
}

.box {
  text-align: left;
}

* {
  font-family: "Ericsson Hilda", serif;
}

</style>