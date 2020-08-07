<template>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <!--      滑动开关-->
      <switch></switch>
      <!--      侧边栏-->
      <sideMenu @indexSelect="listByCategory" ref="sideMenu"></sideMenu>
    </el-aside>
    <el-main>
      <!--      书-->
      <books class="books-area" ref="booksArea"></books>
    </el-main>
  </el-container>
</template>

<script>
  import sideMenu from './sideMenu'
  import books from './books'

  export default {
    name: 'libraryIndex',
    components: {books, sideMenu},
    methods: {
      listByCategory() {
        var _this = this
        var cid = this.$refs.sideMenu.cid
        var url = 'categories/' + cid + '/books'
        this.$axios.get(url).then(resp => {
          if (resp && resp.status === 200) {
            _this.$refs.booksArea.books = resp.data
          }
        })
      }
    }
  }
</script>

<style scoped>
  .books-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
  }
</style>

