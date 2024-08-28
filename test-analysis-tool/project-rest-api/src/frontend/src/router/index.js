import {createRouter, createWebHistory} from 'vue-router'
import Home from '../views/Home.vue'
import ProgrammeDetails from '../views/ProgrammeDetails.vue'
import NotFound from "@/views/NotFound";
import ProductDetails from "@/views/ProductDetails";
import CNADetails from "@/views/CNADetails";
import AddProject from "@/views/AddProject";

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/add-project',
        name: 'AddProject',
        component: AddProject
    },
    {
        path: '/programme/',
        name: 'ProgrammeDetails',
        component: ProgrammeDetails,
        props: true
    },
    {
        path: '/programme/product/',
        name: 'ProductDetails',
        component: ProductDetails,
        props: true
    },
    {
        path: '/programme/product/cna/',
        name: 'CNADetails',
        component: CNADetails,
        props: true
    },
    // Error 404
    {
        path: '/:catchAll(.*)',
        name: 'NotFound',
        component: NotFound
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
