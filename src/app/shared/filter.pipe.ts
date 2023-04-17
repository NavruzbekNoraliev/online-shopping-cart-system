import { Pipe, PipeTransform } from '@angular/core';


@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(value: any[], filterString: string, propertyName: string): any {
    const result: any = [];
    if(!value || filterString === '' || propertyName === '') {
      return value;
    } 
    value.forEach((a: any) => {
      if(a[propertyName].trim().toLowerCase().includes(filterString.toLowerCase())) {
        result.push(a);
      }
    });
    return result;
  }

}
