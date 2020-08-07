<template>
  <div>
    <nav-menu></nav-menu>
    <router-view/>
  </div>
</template>

<script>
  import navMenu from './common/navMenu'
  export default {
    name: 'home',
    components: {navMenu}
  }
</script>

<style scoped>

</style>

