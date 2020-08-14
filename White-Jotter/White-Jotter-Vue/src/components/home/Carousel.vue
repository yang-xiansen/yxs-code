<template>
  <el-card class="card-carousel">
  <el-carousel  class="push" :interval="4000" arrow="always">
    <el-carousel-item v-for="item in items" :key="item.id">
      <a :href=item.link target="_blank">
        <img :src=item.img alt="" class="carousel_img">
      </a>
      <h3>{{item.title}}</h3>
    </el-carousel-item>
  </el-carousel>
  </el-card>
</template>

<style>
  .el-carousel__item h3 {
    color: black;
    font-size: 15px;
    /*line-height: 300px;*/
    bottom: 43px;
    float: left;
    position: relative;
  }

  .card-carousel {
    /*margin: 0 auto;*/
    margin-left: 20px;
    width: 680px;
    height: 320px;
  }

  .push {
    /*margin-left: -15px;*/
  }

  .el-carousel__item:nth-child(2n) {
    background: black;
  }

  .el-carousel__item:nth-child(2n+1) {
    background: black;
  }

  .carousel_img {
    height: 100%;
    width: 100%;
    text-align: center;
  }
</style>

<script>
  export default {
    name: 'Carousel',
    data: function () {
      return {
        items: [{
          id: 1,
          title: '美女',
          img: '../../../static/img/carousel/meinv.jpg',
          link: 'https://gitee.com/yangxianshen/yxs-code/tree/master/White-Jotter'
        },
        {
          id: 2,
          title: 'Vue.js - 渐进式 JavaScript 框架',
          img: '../../../static/img/carousel/vue.png',
          link: 'https://cn.vuejs.org/'
        },
        {
          id: 3,
          title: 'element-ui - 网站快速成型工具',
          img: '../../../static/img/carousel/element.png',
          link: 'http://element-cn.eleme.io/#/zh-CN'
        }]
      }
    }
  }
</script>
