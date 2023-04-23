import { Injectable } from '@angular/core';

export interface BadgeItem {
  type: string;
  value: string;
}
export interface Saperator {
  name: string;
  type?: string;
}
export interface ChildrenItems {
  state: string;
  name: string;
  type?: string;
}

export interface Menu {
  state: string;
  name: string;
  type: string;
  icon: string;
  badge?: BadgeItem[];
  saperator?: Saperator[];
  children?: ChildrenItems[];
}

const MENUITEMS = [
  {
    state: 'vendor',
    name: 'Approve Vendor',
    type: 'link',
    icon: 'how_to_reg'
  }, {
    state: 'approve_product',
    name: 'Approve Product',
    type: 'link',
    icon: 'inventory'
  }, {
    state: 'payment',
    name: 'One-time payment',
    type: 'link',
    icon: 'attach_money'
  },
  {
    state: 'reports',
    name: 'Reports',
    type: 'link',
    icon: 'bar_chart_4_bars'
  },{
    state: 'order_history',
    name: 'Order History',
    type: 'link',
    icon: 'history'
  },{
    state: 'settings',
    name: 'Settings',
    type: 'link',
    icon: 'settings'
  }
];

@Injectable()

export class MenuItems {
  getMenuitem(): Menu[] {
    return MENUITEMS;
  }

}
