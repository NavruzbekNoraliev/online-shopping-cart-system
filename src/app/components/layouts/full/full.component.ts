import { MediaMatcher } from '@angular/cdk/layout';
import { ChangeDetectorRef, Component, OnDestroy, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Observable, debounceTime, fromEvent, merge, tap } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { MenuItems } from 'src/app/shared/menu-items';

/** @title Responsive sidenav */
@Component({
  selector: 'app-full-layout',
  templateUrl: 'full.component.html',
}) 
export class FullComponent implements OnDestroy, AfterViewInit {
  mobileQuery: MediaQueryList;    
  sidebarOpened: boolean = false;
  @ViewChild('sidebarbtn', { read: ElementRef }) sidebarBtn!: ElementRef;
  @ViewChild('snav') sidenav!: MatSidenav;
  sidebarMenuEvents$!: Observable<any>;
  
  private _mobileQueryListener: () => void;
  
  constructor(
    private authService: AuthService,
    changeDetectorRef: ChangeDetectorRef, 
    media: MediaMatcher, public menuItems: MenuItems) {
    this.mobileQuery = media.matchMedia('(min-width: 768px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  authorized(): boolean {
    return this.authService.isAuthorized;
  }


  handleSidebarMenuClick() {
    const el = this.sidebarBtn.nativeElement;
    const clickEvent = fromEvent<MouseEvent>(el, 'click');
    const dblClickEvent = fromEvent<MouseEvent>(el, 'dblclick');
    const eventsMerged = merge(clickEvent, dblClickEvent).pipe(debounceTime(200));
    this.sidebarMenuEvents$ = eventsMerged.pipe(tap((event) => {
      if (event.type === 'click') {
        this.sidenav.toggle()
      } else {
        console.log("handle to toggle mini")
        // this.settings.toggleMini()
      }
    }));
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }
 ngAfterViewInit() {
     this.handleSidebarMenuClick()
 } 
   
}
