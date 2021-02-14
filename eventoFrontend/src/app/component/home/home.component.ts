import { OnInit, AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { Events } from 'src/app/model/events';
import { EventsService } from 'src/app/service/events.service';
import { UnitsService } from 'src/app/service/units.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {
  data: Events[];

  displayedColumns: string[] = ['name', 'startTime', 'endTime', 'freeEntrance', 'city', 'citySize', 'county', 'region', 'cityActive'];

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: false }) sort: MatSort;

  obs: Observable<any>;
  dataSource: MatTableDataSource<Events> = new MatTableDataSource<Events>([]);

  constructor(private eventsService: EventsService, private unitsService: UnitsService) {

  }

  ngOnInit() {
    this.eventsService.getEvents().subscribe(data => {      
      this.data = data;
      this.dataSource = new MatTableDataSource(this.data);      
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  ngAfterViewInit() {
    this.unitsService.apiData$.subscribe((data) => {
      console.log("from ngAfterViewInit home")
      this.data = data;
      this.dataSource = new MatTableDataSource<Events>(this.data);
      this.obs = this.dataSource.connect();
      if (this.data != null) {
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;        
      }
    })
  }

}