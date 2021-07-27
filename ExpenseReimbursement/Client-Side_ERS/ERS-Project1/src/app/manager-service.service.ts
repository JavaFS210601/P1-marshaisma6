import { LoginServiceService } from './login-service.service';
import { Injectable } from '@angular/core';
import { Reimbursement } from './reimbursement';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';


const HTTP_OPTIONS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })}


@Injectable({
  providedIn: 'root'
})
export class ManagerServiceService {

  request: Reimbursement;
  allEmployees:User[];
  currentEmployee:User;
  
  constructor(private http: HttpClient,private loginService: LoginServiceService) { }


  getAllRequests():Observable<Reimbursement[]>{
    return this.http.get<Reimbursement[]>("http://localhost:8008/P1Final/reimbursement", HTTP_OPTIONS);
  }

  getAllRequestsByStatus(status:string):Observable<Reimbursement[]>{
    return this.http.get<Reimbursement[]>("http://localhost:8008/P1Final//status?status=" + status, HTTP_OPTIONS);
  }

  getAllRequestsById(id:number):Observable<Reimbursement[]>{
    return this.http.get<Reimbursement[]>("" +id, HTTP_OPTIONS);
  }

  getAllRequestByStatusId(id:number,status:string):Observable<Reimbursement[]>{
    return this.http.get<Reimbursement[]>(" " +id +"&status="+status, HTTP_OPTIONS);
  }

  updateRequestStatus(request:Reimbursement):Observable<Reimbursement>{
    return this.http.post<Reimbursement>("http://localhost:8008/P1Final//reimbursement/id/status", request, HTTP_OPTIONS);
  }


  getAllEmployees():Observable<User[]>{
    return this.http.get<User[]>("http://localhost:8008/P1Final/employee", HTTP_OPTIONS);
  }
  createEmployee(employee:User):Observable<User[]>{
    return this.http.post<User[]>("http://localhost:8008/P1Final/employee", employee,HTTP_OPTIONS);
  }

  storeAllEmployees(employees:User[]){
    this.allEmployees = employees;
  }

  getStoredEmployees():User[]{
    return this.allEmployees;
  }

  saveCurrent(employee:User){
      this.currentEmployee = employee;
  }

  getCurrent():User{
    return this.currentEmployee;
  }
}
