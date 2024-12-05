<script setup>
import { ref, useTemplateRef } from "vue";
import { Search, Edit, Plus, Delete } from '@element-plus/icons-vue'
import request from "@/api";
import EmployeeForm from "@/component/EmployeeForm.vue";


const titles = ref([
    {
        label: "All",
        value: null
    },
    {
        label: "HR",
        value: 1
    }, {
        label: "IT",
        value: 2
    }, {
        value: 3,
        label: "Finance"
    }, {
        value: 4,
        label: "Marketing"
    }, {
        value: 5,
        label: "Operations"
    }]);

const employees = ref([])
const params = ref({
    deptId: null,
    hireDate: []
})
//分页
const pagePagination = ref({
    currentPage: 1,
    pageSize: 5,
    total: 0,
    pages: 1
})
const handleSizeChange = (size) => {
    pagePagination.value.pageSize = size;
    getEmployees();
};
const handleCurrentChange = (page) => {
    pagePagination.value.currentPage = page;
    getEmployees();
};
const getEmployees = async () => {
    params.value['currentPage'] = pagePagination.value.currentPage
    params.value['pageSize'] = pagePagination.value.pageSize
    const res = await request.post('/ymz/search', params.value)
    employees.value = res.data.data.records
    pagePagination.value.total = res.data.data.total
    pagePagination.value.pages = res.data.data.pages
}
getEmployees()

let removedIds = ref([])
const selectChangeHandle = (params) => {
    //param是所有被选中的行的具体信息，只需将id 提取出来即可
    removedIds.value = params.map(item => item.empId)
}
const doDelete = async () => {
    if (removedIds.value.length === 0) {
        ElMessage({
            type: 'error',
            message: '您并没有选择任何员工！',
        })
        return
    }

    await ElMessageBox.confirm(
        '确定删除这些员工吗',
        '警告',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            //踩坑记录：Delete请求默认不包含请求体。需要使用下面的写法才能在Delete请求中使用请求体
            await request.delete('/ymz/employees', { data: removedIds.value, headers: { 'Content-Type': 'application/json' } })
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
            getEmployees()
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '删除取消',
            })
        })
}

let emptyEmployee = {
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    hireDate: undefined,
    jobTitle: undefined,
    salary: undefined,
    deptId: ""
}
//添加员工
const showAddEmployeeDialog = ref(false)
const newEmployee = ref(emptyEmployee)
const addForm = useTemplateRef('addForm')
const doAdd = async () => {
    await addForm.value.validate()
    await ElMessageBox.confirm(
        '确定添加员工吗',
        '警告',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            await request.post('/ymz/employees', newEmployee.value)
            ElMessage({
                type: 'success',
                message: '添加成功',
            })
            getEmployees()
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消添加',
            })
        }).finally(() => {
            showAddEmployeeDialog.value = false
            newEmployee.value = emptyEmployee
        })
}


//编辑员工
const showEditEmployeeDialog = ref(false)
const editEmployee = ref(emptyEmployee)
const editForm = useTemplateRef('editForm')
let employeeId = -1
const doEditPre = async (row) => {
    //编辑前的预处理：打开弹窗、回显数据
    showEditEmployeeDialog.value = true
    editEmployee.value = row
    employeeId = row.empId
}
const doEdit = async () => {
    await editForm.value.validate()
    await ElMessageBox.confirm(
        '确定修改员工信息吗',
        '警告',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            await request.put('/ymz/employees/' + employeeId, editEmployee.value)
            ElMessage({
                type: 'success',
                message: '修改成功',
            })
            getEmployees()
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消修改',
            })
        }).finally(() => {
            clearEdit()
        })
}
const clearEdit = () => {
    editEmployee.value = emptyEmployee
    showEditEmployeeDialog.value = false
    employeeId = -1
}

</script>

<template>
    <div style="padding: 5px">
        <el-container direction="vertical">
            <el-row style="">
                <el-col :span="6">
                    <el-form-item label="部门" prop="deptId">
                        <el-select placeholder="请选择部门" style="width: 100%;" v-model="params.deptId">
                            <el-option v-for="(item, index) in titles" :key="index" :label="item.label"
                                :value="item.value" :disabled="item.disabled"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="雇佣日期" prop="hireDate">
                        <el-date-picker placeholder="请选择雇佣日期" type="daterange" style="width: 100%;"
                            v-model="params.hireDate">
                        </el-date-picker>
                    </el-form-item>
                </el-col>
                <el-col :span="4" :offset="2">
                    <el-button type="primary" :icon="Search" @click="getEmployees">搜索</el-button>
                </el-col>
            </el-row>
            <el-card>
                <el-table @selection-change="selectChangeHandle" :data="employees"
                    :tooltip-options="{ popperOptions: { strategy: 'fixed' } }">
                    <el-table-column type="selection"></el-table-column>
                    <el-table-column label="名" prop="firstName"></el-table-column>
                    <el-table-column label="姓" prop="lastName"></el-table-column>
                    <el-table-column label="邮箱" prop="email"></el-table-column>
                    <el-table-column label="电话号码" prop="phoneNumber"></el-table-column>
                    <el-table-column label="雇佣日期" prop="hireDate"></el-table-column>
                    <el-table-column label="职位" prop="jobTitle"></el-table-column>
                    <el-table-column label="薪水" prop="salary"></el-table-column>
                    <el-table-column label="所属部门" prop="deptName"></el-table-column>
                    <el-table-column label="操作">
                        <template #default="{ row }">
                            <el-button type="primary" text :icon="Edit" @click="doEditPre(row)">编辑</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <template #header>
                    <el-button type="success" :icon="Plus" @click="showAddEmployeeDialog = true">新增</el-button>
                    <el-button type="danger" :icon="Delete" @click="doDelete">删除</el-button>
                </template>
                <template #footer>
                    <el-pagination v-model:current-page="pagePagination.currentPage"
                        v-model:page-size="pagePagination.pageSize" :page-sizes="[5, 10]" background
                        layout="total, sizes, prev, pager, next, jumper" :total="pagePagination.total"
                        @size-change="handleSizeChange" @current-change="handleCurrentChange" />
                </template>
            </el-card>
        </el-container>

        <!-- 添加员工弹窗 -->
        <el-dialog v-model="showAddEmployeeDialog">
            <EmployeeForm v-model="newEmployee" ref="addForm">
            </EmployeeForm>
            <template #header>
                <h1>添加新员工</h1>
            </template>
            <template #footer>
                <el-button type="success" @click="doAdd">确定</el-button>
                <el-button type="danger" @click="showAddEmployeeDialog = false">取消</el-button>
            </template>
        </el-dialog>
        <!-- 编辑员工弹窗 -->
        <el-dialog v-model="showEditEmployeeDialog">
            <EmployeeForm v-model="editEmployee" ref="editForm">
            </EmployeeForm>
            <template #header>
                <h1>编辑员工</h1>
            </template>
            <template #footer>
                <el-button type="success" @click="doEdit">确定</el-button>
                <el-button type="danger" @click="clearEdit">取消</el-button>
            </template>
        </el-dialog>
    </div>
</template>
