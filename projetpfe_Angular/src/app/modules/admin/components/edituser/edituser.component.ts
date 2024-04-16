import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
export class EdituserComponent implements OnInit {
  user = {
    id: null,
    username: '',
    email: '',
    creationDate: '',
    token: null,
    tokenCreationDate: null,
    activated: false,
    numtele: null,
    country: '',
    roles: {}
  };
  userId!: any;
  roles: any[] = [];
  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('id');
    if (this.userId) {
      this.userService.getUser(this.userId).subscribe(user => {
        this.user = user;
      });
    }

  }

  getUser(id: number): void {
    this.userService.getUser(id).subscribe(user => {
      this.user = user;
    });
  }



  updateUser(): void {
    this.userService.updateUser(this.userId, this.user).subscribe(
      response => {
      },
      error => {
      }
    );
    this.router.navigate(['/admin']);
  }
}
