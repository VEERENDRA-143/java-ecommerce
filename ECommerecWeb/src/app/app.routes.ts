import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { NgModule } from '@angular/core';
import { CustomerModule } from './customer/customer.module';
import { AdminModule } from './admin/admin.module';

export const routes: Routes = [
    {path:"login",component:LoginComponent},
    {path:"signup",component:SignupComponent},
    {path:"customer",component:CustomerModule},
    {path:"admin",component:AdminModule}
];
