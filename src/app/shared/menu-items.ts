import { Injectable } from "@angular/core";
import { AuthService } from "../auth/auth.service";

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

const MENUITEMS: Menu[] = [];

@Injectable()
export class MenuItems {
  constructor(private authService: AuthService) {
    if (this.authService.roleType == "ADMIN") {
      MENUITEMS.push(
        {
          state: "vendor",
          name: "Approve Vendor",
          type: "link",
          icon: "how_to_reg",
        },
        {
          state: "approve_product",
          name: "Approve Product",
          type: "link",
          icon: "inventory",
        },
        {
          state: "reports",
          name: "Reports",
          type: "link",
          icon: "bar_chart_4_bars",
        }
      );
    } else if (this.authService.roleType == "VENDOR") {
      MENUITEMS.push(
        {
          state: "payment",
          name: "One-time payment",
          type: "link",
          icon: "attach_money",
        },
        {
          state: "reports",
          name: "Reports",
          type: "link",
          icon: "bar_chart_4_bars",
        }
      );
    } else if (this.authService.roleType == "CUSTOMER") {
      MENUITEMS.push(
        {
          state: "report/order-history",
          name: "Order History",
          type: "link",
          icon: "history",
        },
        {
          state: "settings",
          name: "Settings",
          type: "link",
          icon: "settings",
        }
      );
    }
  }
  getMenuitem(): Menu[] {
    return MENUITEMS;
  }
}
