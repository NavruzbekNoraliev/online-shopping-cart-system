import { ChangeDetectorRef, Component } from '@angular/core';

import { MediaMatcher } from '@angular/cdk/layout';
import { MenuItems } from 'src/app/shared/menu-items';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {
  mobileQuery: MediaQueryList;

  private _mobileQueryListener: () => void;

  constructor(
    private authAPI: AuthService,
    private router: Router,
    changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, public menuItems: MenuItems) {
    this.mobileQuery = media.matchMedia('(min-width: 768px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  get email(): string {return this.authAPI.email}
  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  openRoute(state: string) {
    if (state.includes('/')) {
      let newState = state.split('/')
      this.router.navigate(['/', ...newState])
    } else this.router.navigate(['/', state])
  }

}