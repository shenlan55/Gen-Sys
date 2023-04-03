<template>
  <i-frame :src="openUrl" />
</template>

<script>
  import { getToken } from '@/utils/auth'
  import iFrame from "@/components/iFrame/index";

  export default {
    name: 'JimuReportView',
    components: { iFrame },
    data() {
      return {
        openUrl: '',
      }
    },
    created() {
      const reportId = this.$route.path.substring(this.$route.path.lastIndexOf("/")+1)
      this.openUrl = process.env.VUE_APP_BASE_API + '/jmreport/view/' + reportId + '?token=Bearer ' +  getToken()
    }
  }
</script>

<style scoped>

</style>

