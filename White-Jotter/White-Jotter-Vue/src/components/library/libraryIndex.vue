<template>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">

      <!--      滑动开关-->
      <switch></switch>

      <!--      侧边栏-->
      <sideMenu></sideMenu>
    </el-aside>
    <el-main>
      <!--      书-->
      <books class="books-area"></books>
    </el-main>
  </el-container>
</template>

<script>
  import sideMenu from './sideMenu'
  import books from './books'

  export default {
    name: 'libraryIndex',
    components: {sideMenu, books}
  }
</script>

<style scoped>
  .books-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
  }
</style>


