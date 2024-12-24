import { Component} from '@angular/core';
import {  FormControl, FormGroup, FormsModule, MinLengthValidator, ReactiveFormsModule, Validators } from '@angular/forms';
import {MdbFormsModule} from "mdb-angular-ui-kit/forms";

import { login } from '../../types/login';
import { LoginService } from '../../services/login.service';
import {  ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MdbFormsModule, 
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm!: FormGroup;
  login!: login;
  disabled = true;

  constructor( 
    private loginService: LoginService,
    private toastrService: ToastrService,
    private router: Router
  ) {
    this.loginForm = new FormGroup({
      email: new FormControl('',[Validators.required, Validators.email]),
      password: new FormControl('',[Validators.required, Validators.minLength(3)]),
    });

    //Monitora quando o form é válido
    this.loginForm.statusChanges.subscribe(
      status => { 
        this.disabled = status !== 'VALID';
      }
    );
  }

  onSubmit() {
    this.login = this.loginForm.value;
    this.loginService.login(this.login)
      .subscribe({
        next: (token) => {
          let credentialTokenJson = JSON.parse(token); 
          this.loginService.addToken(credentialTokenJson.token);
          this.loginService.addName(credentialTokenJson.name);
          this.toastrService.success('Logado com sucesso');
          if (this.loginService.hasPermission('ROLE_ADMINISTRATOR')){
            this.router.navigate(['/dashboard/admin']);
          }else {
            this.router.navigate(['/dashboard/home']);
          }
        
        },
        error: () => this.toastrService.error('Erro ao logar'),
      });
  }
}
