import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';
import { environment } from '../environments/environment';

const HTTP_OPTIONS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })}
  
@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  dbUser: User;


  constructor(private http: HttpClient) { }



  validLoginUser(user:User): Observable<User>{
  
    return this.http.post<User>('http://localhost:8008/P1Final/login', user, HTTP_OPTIONS);
  }

  getUser():User{
    return this.dbUser
  }

  saveUser(save:User):void{
    this.dbUser = save;
  }

  getEmployeeById(id:Number):Observable<User>{
    return this.http.get<User>("http://localhost:8008/P1Final/employee/id?id=" + id, HTTP_OPTIONS);
  }
}