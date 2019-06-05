<template>
    <div>
      <my-header ></my-header>
      <router-view></router-view>
    </div>
</template>

<script>
  import myHeader from './MyHeader'
    export default {
        name: "CommonPublic",
        components:{
          myHeader
      },
        methods:{
        }
    }
</script>

<style scoped>

</style>
