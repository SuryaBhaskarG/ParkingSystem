import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent  {

  email: string;

  /*constructor(private http: HttpClient) { }*/
  constructor(private http: HttpClient, private router: Router) { }

  /*onSubmit() {
    const params = new HttpParams().set('email', this.email);
    this.http.put(`http://localhost:8080/users/forget-password`, {}, { params })
      .subscribe(response => {
        console.log(response);
      });
  }*/
  /*
  onSubmit(forgotPasswordForm: NgForm) {
    const params = new HttpParams().set('email', this.email);
    this.http.put(`http://localhost:8080/users/forget-password`, {}, { params, responseType: 'text' })
      .subscribe(
        response => {
          console.log(response);
          alert(response);
          forgotPasswordForm.resetForm();
          this.router.navigate(['/resetpasswordpage']);
        },
        error => {
          console.error(error);
          alert('An error occurred!');
        }
      );
  }

  onSubmit() {
    const email = 'gsurya1412@gmail.com'; // replace with the actual email
    const newPassword = this.passwordResetForm.get('password').value;
    const params = new HttpParams().set('email', email);
    const headers = new HttpHeaders().set('newPassword', newPassword);
    this.http.put(`http://localhost:8080/users/set-password`, {}, { params, headers })
      .subscribe(
        response => {
          console.log('Response:', response);
          alert('Password set successfully!');
          this.passwordResetForm.reset();
          this.router.navigate(['/loginpage']);
        },
        error => {
          console.error('Error:', error);
          alert('An error occurred!');
        }
      );
  }

}*/

onSubmit(forgotPasswordForm: NgForm) {
  const params = new HttpParams().set('email', this.email);
  this.http.put(`http://localhost:8080/users/forget-password`, {}, { params, responseType: 'text' })
    .subscribe(
      response => {
        console.log(response);
        alert(response);
        forgotPasswordForm.resetForm();
        this.router.navigate(['/password-reset']);
      },
      error => {
        console.error(error);
        alert('An error occurred!');
      }
    );
}
}