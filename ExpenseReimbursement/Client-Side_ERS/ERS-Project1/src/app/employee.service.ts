import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Reimbursement } from './reimbursement';
import { LoginServiceService } from './login-service.service';
import { Observable } from 'rxjs';
import { User } from './user';

const HTTP_OPTIONS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })}


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  request: Reimbursement;
  
  constructor(private http: HttpClient,private loginService: LoginServiceService) { }


  createRequest(request:Reimbursement):Observable<Reimbursement>{

    return this.http.post<Reimbursement>("http://localhost:8008/P1Final/reimbursement", request, HTTP_OPTIONS);
  }

  getAllRequestsById(id:number):Observable<Reimbursement[]>{
    return this.http.get<Reimbursement[]>("" +id, HTTP_OPTIONS);
  }

  getAllRequestByStatusId(id:number,status:string):Observable<Reimbursement[]>{
    return this.http.get<Reimbursement[]>("http://localhost:8008/P1Final//reimbursement/id/status?userId=" +id +"&status="+status, HTTP_OPTIONS);
  }

  updateName(employee:User):Observable<User>{
    return this.http.post<User>("http://localhost:8008/P1Final/employee/id", employee, HTTP_OPTIONS);
  }

  updatePassword(employee:User):Observable<User>{
    return this.http.post<User>("http://localhost:8008/P1Final/employee/id/password", employee, HTTP_OPTIONS);
  }
}
