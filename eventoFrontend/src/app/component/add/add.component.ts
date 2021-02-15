import { QueryList, ViewChild, ViewChildren } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, FormsModule, Validators } from '@angular/forms';
import { MatPaginator, MatSlideToggle, MatSort, MatTableDataSource } from '@angular/material';
import { Observable } from 'rxjs';
import { City } from 'src/app/model/city';
import { County } from 'src/app/model/county';
import { Events } from 'src/app/model/events';
import { Region } from 'src/app/model/region';
import { AddService } from 'src/app/service/add.service';
import { UnitsService } from 'src/app/service/units.service';

interface RegionType {
  value: number;
  viewValue: string;
}

interface SizeValue {
  value: number;
  viewValue: string;
}

interface Regions {
  id: number;
  name: string;
  description: string;
}

interface Counites {
  id: number;
  name: string;
  description: string;
  organizationalUnit: number;
}

interface Cities {
  id: number;
  name: string;
  size: string;
  active: string;
  organizationalUnit: string;
}

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  dataRegion: any[];
  dataCounty: any[];
  dataCity: any[];
  dataEvent: any[];

  regionTypes: RegionType[] = [
    { value: 1, viewValue: "REGIJA" },
    { value: 2, viewValue: "ŽUPANIJA" }
  ];

  sizeValues: SizeValue[] = [
    { value: 1, viewValue: "MALI" },
    { value: 2, viewValue: "SREDNJI" },
    { value: 3, viewValue: "VELIK" }
  ];

  checked = true;  

  displayedColumnsRegions: string[] = ['id', 'name', 'description', 'edit'];
  displayedColumnsCounties: string[] = ['id', 'name', 'description', 'organizationalUnit', 'edit'];
  displayedColumnsCities: string[] = ['cityId', 'cityName', 'sizeValue', 'active', 'organizationUnit', 'edit'];
  displayedColumnsEvents: string[] = ['name', 'startTime', 'endTime', 'freeEntrance', 'city', 'edit'];

  @ViewChildren(MatPaginator) paginator = new QueryList<MatPaginator>();
  @ViewChildren(MatSort) sort = new QueryList<MatSort>();

  dataSourceRegions: MatTableDataSource<Regions> = new MatTableDataSource<Regions>([]);
  dataSourceCounties: MatTableDataSource<Counites> = new MatTableDataSource<Counites>([]);
  dataSourceCities: MatTableDataSource<Cities> = new MatTableDataSource<Cities>([]);
  dataSourceEvents: MatTableDataSource<Events> = new MatTableDataSource<Events>([]);

  regions$: Observable<Region[]>;
  counties$: Observable<County[]>;
  cities$: Observable<City[]>;

  addRegionForm = new FormGroup({
    regionType: new FormControl(),
    region: new FormControl(),
    description: new FormControl()
  });

  addCountyForm = new FormGroup({
    regionType: new FormControl(),
    regions$: new FormControl(),
    county: new FormControl(),
    description: new FormControl()
  });

  addCityForm = new FormGroup({
    counties$: new FormControl(Validators.required),
    city: new FormControl('', Validators.required),
    size: new FormControl(Validators.required),
    active: new FormControl(null ? false : true)
  });

  addEventForm = new FormGroup({
    cities$: new FormControl(),
    event: new FormControl('',Validators.required),
    eventStartDate: new FormControl(Validators.required),
    eventEndDate: new FormControl(),
    freeEntrance: new FormControl(null ? true : false)
  });

  constructor(private addService: AddService, private unitService: UnitsService) { }

  ngOnInit() {

    this.regions$ = this.unitService.getRegions();
    this.counties$ = this.unitService.getAllCounties();
    this.cities$ = this.unitService.getAllCities();

    this.addService.getEvents().subscribe(dataEvent => {      
      this.dataEvent = dataEvent;      
      this.dataSourceEvents = new MatTableDataSource(this.dataEvent);      
      this.dataSourceEvents.paginator = this.paginator.toArray()[0];
      this.dataSourceEvents.sort = this.sort.toArray()[0];
    });

    this.addService.getCities().subscribe(dataCity => {      
      this.dataCity = dataCity;         
      this.dataSourceCities = new MatTableDataSource(this.dataCity);      
      this.dataSourceCities.paginator = this.paginator.toArray()[1];
      this.dataSourceCities.sort = this.sort.toArray()[1];
    });

    this.addService.getCounties().subscribe(dataCounty => {      
      this.dataCounty = dataCounty;      
      this.dataSourceCounties = new MatTableDataSource(this.dataCounty);      
      this.dataSourceCounties.paginator = this.paginator.toArray()[2];
      this.dataSourceCounties.sort = this.sort.toArray()[2];
    });
    
    this.addService.getRegions().subscribe(dataRegion => {      
      this.dataRegion = dataRegion;      
      this.dataSourceRegions = new MatTableDataSource(this.dataRegion);      
      this.dataSourceRegions.paginator = this.paginator.toArray()[3];
      this.dataSourceRegions.sort = this.sort.toArray()[3];
    });
  }

  submitEvent() {
    console.log("submit event");
      if(this.addEventForm.valid){
        console.log(this.addEventForm.value);
        this.addService.addEvent(this.addEventForm.value);
        this.addEventForm.reset();
      } else {
        console.log("no valid");
      }
    
  }

  submitCity() {
    console.log("submit city");    
    this.addService.addCity(this.addCityForm.value).subscribe(res => {
      this.addService.setData(res);
      this.dataCity = res;
      console.log("before table");
      console.log(this.dataCity);
      this.dataSourceCities = new MatTableDataSource(this.dataCity);
      this.dataSourceCities.paginator = this.paginator.toArray()[1];
      this.dataSourceCities.sort = this.sort.toArray()[1];
    });    
    this.addCityForm.reset();
  }

  submitCounty() {
    console.log("submit county");
  }

  submitRegion() {
    console.log("submit region");
  }

}
