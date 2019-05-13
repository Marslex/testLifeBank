import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Account } from '../model/account';
import { Observable } from 'rxjs/Observable';
 
@Injectable()
export class AccountService {
 
  private accountUrl: string;
 
  constructor(private http: HttpClient) {
    this.accountUrl = 'http://localhost:90/accounts';
  }
 
  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.accountUrl);
  }
 
  public save(user: User) {
    return this.http.post<User>(this.accountUrl, user);
  }
}