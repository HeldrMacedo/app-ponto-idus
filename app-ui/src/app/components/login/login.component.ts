import { Component, Inject } from '@angular/core';
import { Form, FormControl, FormGroup, FormsModule, MinLengthValidator, ReactiveFormsModule, Validators } from '@angular/forms';
import {MdbFormsModule} from "mdb-angular-ui-kit/forms";
import { min } from 'rxjs';
import { login } from '../../types/login';
import { LoginService } from '../../services/login.service';
import { Toast, ToastrService } from 'ngx-toastr';

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
    private toastrService: ToastrService
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
          this.loginService.addToken(token);
          this.loginService.addName(this.login.email);
          this.toastrService.success('Logado com sucesso')
        
        },
        error: () => this.toastrService.error('Erro ao logar'),
      });
  }
}
