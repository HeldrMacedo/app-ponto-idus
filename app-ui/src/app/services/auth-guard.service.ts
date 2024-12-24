import { inject, Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {
  
  loginService = inject(LoginService);
  routes = 
    {
      'ROLE_ADMINISTRATOR': ['/dashboard/admin', '/dashboard/register'],
      'ROLE_USER': ['/dashboard/home']
   };
  constructor(private router: Router) { }


  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const authToken = this.loginService.getToken();
    
    if (authToken) {
      if (this.loginService.hasPermission('ROLE_ADMINISTRATOR') && this.routes.ROLE_ADMINISTRATOR.includes(state.url)) {
        console.log('admin');
        return true;        
      }

      if (this.loginService.hasPermission('ROLE_USER') && this.routes.ROLE_USER.includes(state.url) ) {
        console.log('cliente');
        return true;
      }

      
    } else {
      this.router.navigate(['/login']);
      return false;
    }
    this.router.navigate(['/login']);
    return false
  }
}
