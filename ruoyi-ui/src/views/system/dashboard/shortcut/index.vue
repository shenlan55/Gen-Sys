<template>
  <div>
    <h1>My Application Shortcuts</h1>
    <ul>
      <li v-for="app in list" :key="app.app_id">
        <a :href="app.router">{{ app.name }}</a>
      </li>
    </ul>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'AppShortcuts',
    data() {
      return {
        list: []
      }
    },
    mounted() {
      this.getApplications()
    },
    methods: {
      getApplications() {
        // Fetch data from backend API using Axios
        axios.get('/dev-api/portal/app/api/applications').then(response => {
          this.list = response.data
        }).catch(error => {
          console.log(error)
        })
      }
    }
  }
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
