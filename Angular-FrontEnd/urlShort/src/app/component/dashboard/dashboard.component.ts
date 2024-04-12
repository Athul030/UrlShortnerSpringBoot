import {Component, OnInit} from '@angular/core';
import {UrlShortService} from "../../services/url-short.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{

    url:string = "";
    isUrlGenerated:boolean=false;
    isErrorGenerated:boolean=false;
    shortURL:string = "";
    originalURL:string="";
    isCopied:boolean=false;

    constructor( private  shortService:UrlShortService) {
    }

    ngOnInit(): void {
      this.isUrlGenerated=false;
    }

  generateShortURL(){
    this.shortService.getShortURL(this.url).subscribe(
      (result:any)=>{
        console.log(result.shortLink+"11")
        if(result == null){
          this.isErrorGenerated  =true;
          this.isUrlGenerated = false;
        }else{
          this.isUrlGenerated = true;
          this.isErrorGenerated = false;
          this.shortURL = result.shortLink;
          this.originalURL = result.originalUrl;
        }
      },(error:any)=>{
        this.isUrlGenerated = false;
        this.isErrorGenerated = true;
      }
    )
  }

  copyText() {
    navigator.clipboard.writeText("http://localhost:8080/"+this.shortURL).then(r => console.log(r));
    var button = document.getElementById("copyButton");
    if(button) {
      button.innerHTML = "COPIED";
    }
    this.isCopied=true;

  }




}
