<template>

  <div class="box highest-test-fail-box">
    <div>
      <strong>{{ this.highestTestFail.product }}</strong>
    </div>
    <div class="highest-test-fail-product">
      <strong> {{ this.highestTestFail.nameOfClass }} > {{ this.highestTestFail.testName }}</strong>
    </div>
    <span class="dot failed-tests"></span>
    <div class="failed-tests-text">
      <p><span class="test-text">
        {{ this.highestTestFail.fails }}
      </span> <br> <span class="test-lower"></span></p>
    </div>
  </div>

</template>

<script>

export default {
  props: ["highestTestFail"],
  name: "HighestFailingTest",
  mounted() {
    console.info("Highest Failing Test:", this.highestTestFail);
  }
}

</script>

<style scoped>

.highest-test-fail-box {
  min-width: 100px;
  max-width: 300px;
  margin: 12px;
  text-align: center;
  font-size: 18px;
}

.dot {
  height: 150px;
  width: 150px;
  background-color: white;
  border-radius: 50%;
  display: inline-block;
  margin-top: 8px;
}

.failed-tests {
  height: 106px;
  width: 106px;
  border: #c12127 solid 14px;
  border-radius: 50%;
  box-shadow: 0 0 0 1px #fff inset;
  margin-bottom: 10px;
}

.failed-tests-text {
  letter-spacing: 1px;
  text-align: center;
  display: flex;
  margin-top: -109px;
  margin-left: 117px;
  margin-bottom: 6px;
}

.test-text {
  font-size: 50px;
  font-weight: bold;
}

.test-lower {
  font-weight: bold;
  margin-top: 0 !important;
  font-size: 16px;
}

.highest-test-fail-product {
  padding: 8px;
}

* {
  font-family: "Ericsson Hilda", serif;
}

</style>