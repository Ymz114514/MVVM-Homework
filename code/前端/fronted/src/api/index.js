/* 封装axios用于发送请求 */
import axios from 'axios'


const request = axios.create({
    baseURL: 'http://localhost:8080',
})

// 添加请求拦截器
request.interceptors.request.use(function (config) {

    return config
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error)
})

// 添加响应拦截器
request.interceptors.response.use(function (response) {
    //响应回来了要做些什么
    if(response.data.code===0){
        ElMessage.error(response.data.msg)
        return Promise.reject(response.data.msg)
    }
    return response
}, function (error) {
    ElMessage.error(error.message)
    return Promise.reject(error)
})

export default request