import { Routes, RouterModule } from '@angular/router';
import {AuthorizationGuardService} from './authorization/guard/authorization-guard.service';
import {AuthorizationComponent} from './authorization/authorization.component';
import {MainComponent} from './main/main.component';
import {LoanComponent} from './loan/loan.component';


const appRoutes: Routes = [
  { path: 'main', component: MainComponent, canActivate: [AuthorizationGuardService] },
  { path: 'login', component: AuthorizationComponent },
  { path: 'loan', component: LoanComponent, canActivate: [AuthorizationGuardService] },
  { path: '**', redirectTo: 'main' }
];
export const routing = RouterModule.forRoot(appRoutes);
