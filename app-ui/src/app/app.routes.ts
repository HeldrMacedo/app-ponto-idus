import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeUserComponent } from './components/home-user/home-user.component';
import { AuthGuardService } from './services/auth-guard.service';
import { AdminComponent } from './components/admin/admin.component';
import { PrincipalComponent } from './components/layout/principal/principal.component';
import { RegisterComponent } from './components/register/register.component';

export const routes: Routes = [
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {
        path: 'login', component:  LoginComponent
    },
    {
        path: 'dashboard', component: PrincipalComponent, canActivate: [AuthGuardService], children: [
            { path:'home', component: HomeUserComponent},
            { path: 'admin',component: AdminComponent},
            { path: 'register', component: RegisterComponent }
        ] 
    },
    
];
