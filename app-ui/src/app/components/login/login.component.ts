import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {MdbFormsModule} from "mdb-angular-ui-kit/forms";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

}
