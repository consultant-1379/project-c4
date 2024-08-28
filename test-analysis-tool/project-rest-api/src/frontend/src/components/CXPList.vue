<template>

  <div class="box">
    <article class="media">
      <div class="media-content">
        <div class="content">
          <div>
            <h4>
              {{ parentData.cxpNumber }}
            </h4>
            <h6>
              {{ parentData.cxpName }}
            </h6>
          </div>
          <div class="projectDetails">
            <p>
              {{ parentData.cxpRepo }}
            </p>
            <p>
              {{ parentData.jenkinsJobURL }}
            </p>
          </div>
        </div>
      </div>
    </article>
  </div>

</template>

<script>

export default {
  name: "CXPList",
  props: ['parentData']
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
  min-height: 200px;
  max-width: 350px;
  display: inline-flex;
  min-width: 250px;
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