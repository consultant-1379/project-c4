<template>

  <div class="box">
    <span class="circle"></span>
    <span class="dot total-tests"></span>
    <span class="dot passed-tests"></span>
    <span class="dot failed-tests"></span>
    <span class="dot skipped-tests"></span>
    <div>
      <div class="total-tests-text">
        <p><span class="test-text">{{ this.testReports.totalPasses > 0 ? this.testReports.totalPasses : "--" }}</span>
          <br> <span class="test-lower">PASSED</span>
        </p>
      </div>
      <div class="passed-tests-text">
        <p><span class="test-text">{{ this.testReports.totalPasses > 0 ? this.testReports.totalTests : "--" }}</span>
          <br> <span class="test-lower">TESTS</span></p>
      </div>
      <div class="failed-tests-text">
        <p><span class="test-text">{{ this.testReports.totalPasses > 0 ? this.testReports.totalFails : "--" }}</span>
          <br> <span class="test-lower">FAILED</span>
        </p>
      </div>
      <div class="skipped-tests-text">
        <p><span class="test-text">{{ this.testReports.totalPasses > 0 ? this.testReports.totalSkips : "--" }}</span>
          <br> <span class="test-lower">SKIPPED</span>
        </p>
      </div>
    </div>
  </div>

</template>

<script>

export default {
  props: ['testReports'],
  name: "TestReport"
}

</script>

<style scoped>

.box {
  margin: 12px;
  height: 215px;
  text-align: left;
}

.dot {
  height: 150px;
  width: 150px;
  margin-right: 75px;
  background-color: white;
  border-radius: 50%;
  display: inline-block;
}

.total-tests {
  height: 175px;
  width: 175px;
  border: #80adea solid 18px;
  border-radius: 25%;
  box-shadow: 0 0 0 1px #fff inset;
}

.passed-tests {
  height: 175px;
  width: 175px;
  border: #42b983 solid 18px;
  border-radius: 50%;
  box-shadow: 0 0 0 1px #fff inset;
}

.failed-tests {
  height: 175px;
  width: 175px;
  border: #c12127 solid 18px;
  border-radius: 50%;
  box-shadow: 0 0 0 1px #fff inset;
}

.skipped-tests {
  height: 175px;
  width: 175px;
  border: #ffe87c solid 18px;
  border-radius: 50%;
  box-shadow: 0 0 0 1px #fff inset;
}

.total-tests-text {
  letter-spacing: 1px;
  text-align: center;
  display: flex;
  margin-top: -141px;
  margin-left: 62px;
}

.total-tests-text {
  letter-spacing: 1px;
  text-align: center;
  display: flex;
  margin-top: -140px;
  margin-left: 304px;
}

.passed-tests-text {
  letter-spacing: 1px;
  text-align: center;
  display: flex;
  margin-top: -102px;
  margin-left: 60px;
}

.failed-tests-text {
  letter-spacing: 1px;
  text-align: center;
  display: flex;
  margin-top: -102px;
  margin-left: 558px;
}

.skipped-tests-text {
  letter-spacing: 1px;
  text-align: center;
  display: flex;
  margin-top: -102px;
  margin-left: 800px;
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

* {
  font-family: "Ericsson Hilda", serif;
}

</style>