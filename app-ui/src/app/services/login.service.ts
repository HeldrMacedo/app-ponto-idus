import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { login } from '../types/login';
import { Observable } from 'rxjs';
import { jwtDecode, JwtPayload } from "jwt-decode";
import { Ususario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  API = 'http://localhost:8080/auth';

  constructor(
    private http: HttpClient
  ) { }

  login(login: login): Observable<string> { 
    return this.http.post<string>(this.API + '/login', login, {responseType: 'text' as 'json'});
  }

  addToken(token: string) {
    localStorage.setItem('token', token);
  }

  addName(name: string){
    localStorage.setItem('name', name);
  }

  removeToken() {
    localStorage.removeItem('token');
  }

  getToken() {
    return localStorage.getItem('token');
  }

  jwtDecode() {
    let token = this.getToken();
    if (token) {
      return jwtDecode<JwtPayload>(token);
    }
    return "";
  }

  hasPermission(role: string) {
    let user = this.jwtDecode() as Ususario;
    if (user.role == role)
      return true;
    else
      return false;
  }
}
