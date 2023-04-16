<template>
  <div class="home-shortcut-container">
    <el-row :gutter="10">
      <el-col
        v-for="shortcut in shortcuts"
        :key="shortcut.router"
        :span="3">
        <el-card shadow="hover" :body-style="{ padding: '0px' }">
          <el-link class="shortcut-link" @click="handleShortcutRedirect(shortcut.router)">
            <svg-icon :icon-class="shortcut.icon" class-name="card-panel-icon"></svg-icon>
            <div class="shortcut-text">
              <span>{{ shortcut.name }}</span>
            </div>
          </el-link>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { getHomeShortcuts } from "@/api/system/user";

export default {
  name: "ShortcutDashboard",
  data () {
    return {
      shortcuts: []
    };
  },
  created() {
    this.loadShortcuts();
  },
  methods: {
    loadShortcuts() {
      getHomeShortcuts().then(response => {
        this.shortcuts = response.data;
      })
    },
    handleShortcutRedirect(router) {
      console.log(router)
      this.$router.push({ name: router })
    }
  }
};
</script>
<style>
.home-shortcut-container .shortcut-link {
  display: block;
  padding: 10px 0px;
  text-align: center;
}
.home-shortcut-container .card-panel-icon {
  width: 36px;
  height: 36px;
  padding: 5px;
}
.home-shortcut-container .shortcut-text {
  padding-top: 5px;
  text-align: center;
  font-size: 14px;
}
</style>
