<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="应用名称" prop="appName">
        <el-input
          v-model="queryParams.appName"
          placeholder="请输入应用名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应用分类" prop="appClass">
        <el-select v-model="queryParams.appClass" placeholder="请选择应用分类" clearable>
          <el-option
            v-for="dict in dict.type.portal_appclass"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="应用路由" prop="router">
        <el-input
          v-model="queryParams.router"
          placeholder="请输入应用路由"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序号" prop="sortnum">
        <el-input
          v-model="queryParams.sortnum"
          placeholder="请输入排序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['portal:app:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['portal:app:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['portal:app:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['portal:app:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="appList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="应用ID" align="center" prop="appId" />
      <el-table-column label="应用名称" align="center" prop="appName" />
      <el-table-column label="应用分类" align="center" prop="appClass">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.portal_appclass" :value="scope.row.appClass"/>
        </template>
      </el-table-column>
      <el-table-column label="应用路由" align="center" prop="router" />
      <el-table-column label="排序号" align="center" prop="sortnum" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['portal:app:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['portal:app:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改门户应用对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="应用名称" prop="appName">
          <el-input v-model="form.appName" placeholder="请输入应用名称" />
        </el-form-item>
        <el-form-item label="应用分类" prop="appClass">
          <el-select v-model="form.appClass" placeholder="请选择应用分类">
            <el-option
              v-for="dict in dict.type.portal_appclass"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="应用路由" prop="router">
          <el-input v-model="form.router" placeholder="请输入应用路由" />
        </el-form-item>
        <el-form-item label="排序号" prop="sortnum">
          <el-input v-model="form.sortnum" placeholder="请输入排序号" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listApp, getApp, delApp, addApp, updateApp } from "@/api/portal/app";

  export default {
    name: "App",
    dicts: ['portal_appclass', 'sys_normal_disable'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 门户应用表格数据
        appList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          appName: null,
          appClass: null,
          router: null,
          sortnum: null,
          status: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          appName: [
            { required: true, message: "应用名称不能为空", trigger: "blur" }
          ],
          appClass: [
            { required: true, message: "应用分类不能为空", trigger: "change" }
          ],
          router: [
            { required: true, message: "应用路由不能为空", trigger: "blur" }
          ],
          sortnum: [
            { required: true, message: "排序号不能为空", trigger: "blur" }
          ],
          createTime: [
            { required: true, message: "创建时间不能为空", trigger: "blur" }
          ],
          status: [
            { required: true, message: "状态不能为空", trigger: "change" }
          ],
          updateTime: [
            { required: true, message: "最后更新时间不能为空", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询门户应用列表 */
      getList() {
        this.loading = true;
        listApp(this.queryParams).then(response => {
          this.appList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          appId: null,
          appName: null,
          appClass: null,
          router: null,
          sortnum: null,
          createTime: null,
          status: null,
          updateTime: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.appId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加门户应用";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const appId = row.appId || this.ids
        getApp(appId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改门户应用";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.appId != null) {
              updateApp(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addApp(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const appIds = row.appId || this.ids;
        this.$modal.confirm('是否确认删除门户应用编号为"' + appIds + '"的数据项？').then(function() {
          return delApp(appIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('portal/app/export', {
          ...this.queryParams
        }, `app_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
