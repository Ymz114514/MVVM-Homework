<script setup>
import { ref,useTemplateRef } from "vue";
const employee = defineModel({
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    hireDate: undefined,
    jobTitle: undefined,
    salary: undefined,
    deptId: ""
})

const employeeRules = {
    firstName: [{
        required: true,
        message: "First Name不能为空",
        trigger: "blur"
    }],
    lastName: [{
        required: true,
        message: "Last Name不能为空",
        trigger: "blur"
    }],
    email: [{
        pattern: /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/,
        message: "Email格式非法",
        trigger: "blur"
    }],
    phoneNumber: [{
        pattern: /^1[3-9]\d{9}$/,
        message: "Phone Number格式非法",
        trigger: "blur"
    }],
    hireDate: [{
        required: true,
        message: "Hire Date不能为空",
        trigger: "change"
    }],
    jobTitle: [{
        required: true,
        message: "Job Title不能为空",
        trigger: "blur"
    }],
    salary: [{
        required: true,
        message: "Salary不能为空",
        trigger: "change"
    }],
    deptId: [{
        required: true,
        message: "DepartMent不能为空",
        trigger: "change"
    }]
};
const depts = ref([{
    label: "HR",
    value: "1"
}, {
    label: "IT",
    value: "2"
}, {
    value: "3",
    label: "Finance"
}, {
    value: "4",
    label: "Marketing"
}, {
    value: "5",
    label: "Operations"
}]);
const form = useTemplateRef("form");

defineExpose({
    validate: () => {
        return new Promise((resolve, reject) => {
            form.value.validate(valid => {
                if (valid) {
                    resolve(true)
                } else {
                    reject(false)
                }
            })
        })
    }
})

</script>

<template>
    <div style="padding: 5px">
        <el-form :model="employee" :rules="employeeRules" label-position="top" ref="form">
            <el-form-item>
                <el-form-item label="First Name" prop="firstName" required>
                    <el-input placeholder="请输入First Name" style="width: 100%;" v-model="employee.firstName">
                    </el-input>
                </el-form-item>
            </el-form-item>
            <el-form-item>
                <el-form-item label="Last Name" prop="lastName" required>
                    <el-input placeholder="请输入Last Name" style="width: 100%;" v-model="employee.lastName"></el-input>
                </el-form-item>
            </el-form-item>
            <el-form-item>
                <el-form-item label="Email" prop="email">
                    <el-input placeholder="请输入Email" style="width: 100%;" v-model="employee.email"></el-input>
                </el-form-item>
            </el-form-item>
            <el-form-item>
                <el-form-item label="Phone Number" prop="phoneNumber">
                    <el-input placeholder="请输入Phone Number" style="width: 100%;" v-model="employee.phoneNumber">
                    </el-input>
                </el-form-item>
            </el-form-item>
            <el-form-item>
                <el-form-item label="Hire Date" prop="hireDate" required>
                    <el-date-picker placeholder="请输入Hire Date" style="width: 100%;" v-model="employee.hireDate">
                    </el-date-picker>
                </el-form-item>
            </el-form-item>
            <el-form-item>
                <el-form-item label="Job Title" prop="jobTitle" required>
                    <el-input placeholder="请输入Job Title" style="width: 100%;" v-model="employee.jobTitle"></el-input>
                </el-form-item>
            </el-form-item>
            <el-form-item>
                <el-form-item label="Salary" prop="salary" required>
                    <el-input-number placeholder="请输入Salary" :min=0  :step=0.01 :precision=2 :controls=false
                        v-model="employee.salary"></el-input-number>
                </el-form-item>
            </el-form-item>
            <el-form-item>
                <el-form-item label="DepartMent" prop="deptId" required>
                    <el-select placeholder="请选择DepartMent" style="width: 100%;" v-model="employee.deptId">
                        <el-option v-for="(item, index) in depts" :key="index" :label="item.label" :value="item.value"
                            :disabled="item.disabled"></el-option>
                    </el-select>
                </el-form-item>
            </el-form-item>
        </el-form>
    </div>
</template>
