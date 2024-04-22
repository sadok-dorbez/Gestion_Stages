import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }
  

  clean(): void {
    window.sessionStorage.clear();
  }

  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return true;
    }

    return false;
  }

  static getToken() {
    return window.localStorage.getItem(TOKEN_KEY);
  }

  saveToken(token: string): void {
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  getToken(): string | null {
    return window.localStorage.getItem(TOKEN_KEY);
  }

  saveUser(user: any): void {
    window.localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  getUser(): any {
    const user = window.localStorage.getItem(USER_KEY);
    return user ? JSON.parse(user) : null;
  }

  clearStorage(): void {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.removeItem(USER_KEY);
  }

  isAdminLoggedIn(): boolean {
    const user = this.getUser();
    return user && user.roles.includes('ROLE_ADMIN');
  }

  isCandidatLoggedIn(): boolean {
    const user = this.getUser();
    return user && user.roles.includes('ROLE_CANDIDAT');
  }

}
