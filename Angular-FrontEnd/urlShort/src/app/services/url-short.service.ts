import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UrlDTO} from "../models";

@Injectable({
  providedIn: 'root'
})
export class UrlShortService {

  serviceUrl:string = "";
  constructor(private http:HttpClient) {
    this.serviceUrl = "http://localhost:8080/generate";
  }

  getShortURL(url:string):any{
    const urlDTO:UrlDTO={
      originalUrl:url
    }
    return this.http.post<any>(this.serviceUrl,urlDTO);
  }
}
