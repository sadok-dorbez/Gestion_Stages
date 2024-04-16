import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { StorageService } from 'src/app/auth/services/storage/storage.service';


const BASIC_URL = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  constructor(private http: HttpClient) { }

  createAuthorizationHeader(): HttpHeaders {
    const token = StorageService.getToken();
    if (token) {
      return new HttpHeaders({
        'Authorization': 'Bearer ' + token
      });
    } else {
      // Gérer le cas où aucun jeton n'est disponible
      // Par exemple, rediriger l'utilisateur vers la page de connexion
      console.error('Aucun jeton d\'accès disponible');
      return new HttpHeaders();
    }
  }


  addSujet(sujet: any): Observable<any> {
    return this.http.post(BASIC_URL + "/api/admin/addSujet", sujet, {
      headers: this.createAuthorizationHeader()
    });
  }


  getAllSujets(): Observable<any> {
      return this.http.get (BASIC_URL + "/api/admin/getAllSujets", { headers: this.createAuthorizationHeader()})
  }

  getSujetById(id: number):Observable<any>{
    return this.http.get (BASIC_URL + "/api/admin/getSujetById/"+ id, {
      headers: this.createAuthorizationHeader()});

  }

  updateSujet(sujetId: number, sujetDto:any):Observable<any>{
    return this.http.put (BASIC_URL + "/api/admin/updateSujet/"+sujetId,sujetDto, {
      headers: this.createAuthorizationHeader()});

  }

  deleteSujet(id:number): Observable<any> {
    return this.http.delete (BASIC_URL + "/api/admin/deleteSujet/"+ id, {
      headers: this.createAuthorizationHeader()
    });
  }


  getAllCandidatures(): Observable<any[]> {
    return this.http.get<any[]>(`${BASIC_URL}/api/admin/getAllCandidatures`);
  }

  getCandidaturesBySujet(idSujet: number): Observable<any[]> {
    return this.http.get<any[]>(`${BASIC_URL}/api/admin/getCandidaturesBySujet/${idSujet}`);
  }

  deleteCandidature(id: number): Observable<any> {
    return this.http.delete(BASIC_URL + "/api/candidat/deleteCandidature/" + id, { headers: this.createAuthorizationHeader() });
  }

  downloadCv(idCandidature: number): Observable<any> {
    return this.http.get(BASIC_URL + "/api/candidat/downloadCv/" + idCandidature, { headers: this.createAuthorizationHeader(), responseType: 'blob' });
  }
}
