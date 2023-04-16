<template>
  <div class="dashboard-editor-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <user-info></user-info>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24">
        <shortcut></shortcut>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24">
        <cms-site-stat></cms-site-stat>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import SysUserInfo from '@/views/system/dashboard/userInfo'
  import SysShortcut from '@/views/system/dashboard/shortcut'

  export default {
    name: 'Index',
    components: {
      'user-info': SysUserInfo,
      'shortcut': SysShortcut,
    },
    data() {
      return {
      }
    },
    created () {
    },
    methods: {

    }
  }
</script>
<style lang="scss" scoped>
  .dashboard-editor-container {
    padding: 10px;
    background-color: rgb(240, 242, 245);
    position: relative;

    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }

  @media (max-width:1024px) {
    .chart-wrapper {
      padding: 8px;
    }
  }
</style>
