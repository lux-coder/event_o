import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective } from '@angular/forms';
import { NgSelectConfig, NgSelectModule } from '@ng-select/ng-select';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs';
import { UnitsService } from 'src/app/service/units.service';
import { Region } from 'src/app/model/region';
import { City } from 'src/app/model/city';
import { County } from 'src/app/model/county';
import { debounceTime, switchMap } from 'rxjs/operators';
import { Events } from 'src/app/model/events';

interface Entrance {
  value: number;
  viewValue: string;
}

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent implements OnInit {

  entrance: Entrance[] = [
    { value: 1, viewValue: "DA"},
    { value: 2, viewValue: "NE" }
  ];

  @ViewChild(FormGroupDirective, { static: false }) searchEvents;

  regions$: Observable<Region[]>;
  selectedRegions: Region[];
  county$: Observable<County[]>;
  selectedCounties: [];
  city$: Observable<City[]>;
  selectedCities: [];

  searchEventForm = new FormGroup({
    regions$: new FormControl(),
    county$: new FormControl(),
    city$: new FormControl(),
    eventName: new FormControl(),
    entrance: new FormControl(),
    eventStartDate: new FormControl(),
    eventEndDate: new FormControl()
  })

  eventName: Observable<any[]>;
  
  public showSeconds = false;
  public enableMeridian = false;
  

  constructor(private unitsService: UnitsService, private config: NgSelectConfig) {
    this.config.notFoundText = 'Custom not found';
    this.config.appendTo = 'body';
    this.config.bindValue = 'value';
  }

  ngOnInit() {
    this.regions$ = this.unitsService.getRegions();
    this.county$ = this.unitsService.getAllCounties();
    this.city$ = this.unitsService.getAllCities();
    this.eventName = this.searchEventForm.controls.eventName.valueChanges.pipe(
      debounceTime(400),
      switchMap(name => this.unitsService.searchEventName(name))
    );
  }

  onChangeRegion(value) {
    console.log("regionChanged");
    this.selectedRegions = value;    
    this.county$ = this.unitsService.getCounties(this.selectedRegions);
  }

  onChangeCounty(value) {
    console.log("countyChanged");    
    this.selectedCounties = value;
    this.city$ = this.unitsService.getCities(this.selectedCounties);
  }

  clearModel() {
    this.selectedRegions = [];
    this.selectedCounties = [];
    this.selectedCities = [];
  }

  submit() {
    console.log("submited");    
    this.unitsService.searchEvents(this.searchEventForm.value).subscribe(res => {
      this.unitsService.setData(res);            
    });
    this.searchEventForm.reset();
  }

  clearForm() {
    console.log("cleared")
    //window.location.reload()
    this.searchEvents.resetForm();
    this.ngOnInit();
    
  }

  
}
