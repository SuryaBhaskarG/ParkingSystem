/*
import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

    passwordResetForm = this.fb.group({
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  
    hide = true; // for password show/hide
  
    constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) { }
  
    ngOnInit() {
    }
  
    onSubmit() {
      const email = 'gsurya1412@gmail.com'; // replace with the actual email
      const newPassword = this.passwordResetForm.get('password').value;
      const params = new HttpParams().set('email', email);
      this.http.put(`http://localhost:8080/users/set-password`, newPassword, { params, headers: { 'Content-Type': 'text/plain' } })
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
}

*/
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  passwordResetForm: FormGroup;
  hide = true;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) { }

  ngOnInit() {
    this.passwordResetForm = this.fb.group({
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

 /* onSubmit() {
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

  onSubmit() {
    const email = 'gsurya1412@gmail.com'; // replace with the actual email
    const newPassword = this.passwordResetForm.get('password').value;
    const params = new HttpParams().set('email', email);
    this.http.put(`http://localhost:8080/users/set-password`, {}, { params, headers: { 'Content-Type': 'text/plain' }, responseType: 'text' })
      .subscribe(
        response => {
          console.log('Response:', response);
          if (response.includes('successfully')) { // Check if response contains success message
            alert('Password set successfully!');
            this.passwordResetForm.reset();
            this.router.navigate(['/loginpage']);
          } else {
            alert('An error occurred!');
          }
        },
        error => {
          console.error('Error:', error);
          alert('An error occurred!');
        }
      );
  }*/
  onSubmit() {
    const email = 'gsurya1412@gmail.com'; // replace with the actual email
    const newPassword = this.passwordResetForm.get('password').value;
    const params = new HttpParams().set('email', email);
    const headers = new HttpHeaders().set('newPassword', newPassword); // Include newPassword header
    this.http.put(`http://localhost:8080/users/set-password`, {}, { params, headers, responseType: 'text' })
      .subscribe(
        response => {
          console.log('Response:', response);
          alert('Password set successfully!');
          this.passwordResetForm.reset();
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Error:', error);
          alert('An error occurred!');
        }
      );
  }
}
  
