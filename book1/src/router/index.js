import Vue from 'vue'
import Router from 'vue-router'
import Main from '../view/Main.vue'
import Category from '../view/Category.vue'
import Book from '../view/Book.vue'
import Logs from '../view/Logs.vue'
import Borrow from '../view/Borrow.vue'
import Users from '../view/User.vue'
import Welcome from '../view/Welcome.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main,
      redirect : '/welcome',
      children:[
        {
          path:'/welcome',
          name: 'welcome',
          component: Welcome
        },
        {
          path: '/category',
          name: 'category',
          component: Category,
        },{
          path: '/book',
          name: 'book',
          component: Book,
        },{
          path: '/borrow',
          name: 'borrow',
          component: Borrow,
        },{
          path: '/users',
          name: 'users',
          component: Users,
        },{
          path: '/logs',
          name: 'logs',
          component: Logs,
        }]
    }
  ]
})
