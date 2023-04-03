<template>
  <i-frame :src="openUrl" id="jimuReportFrame" />
</template>

<script>
  import { getToken } from '@/utils/auth'
  import iFrame from "@/components/iFrame/index";

  export default {
    name: 'JimuReportDesign',
    components: { iFrame },
    data() {
      return {
        openUrl: process.env.VUE_APP_BASE_API + '/jmreport/list?token=Bearer ' +  getToken()
      }
    },
  }
</script>

<style scoped>

</style>

