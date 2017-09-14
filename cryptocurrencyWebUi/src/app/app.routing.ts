import { Routes, RouterModule } from '@angular/router';
import {AuthorizationGuardService} from './authorization/guard/authorization-guard.service';
import {AuthorizationComponent} from './authorization/authorization.component';
import {MainComponent} from './main/main.component';
import {LoanComponent} from './loan/fiatCashLoan/loan.component';
import {RegistrationComponent} from './registration/registration.component';
import {SettingsComponent} from './settings/settings.component';


const appRoutes: Routes = [
  { path: 'login', component: AuthorizationComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'settings', component: SettingsComponent, canActivate: [AuthorizationGuardService] },
  { path: 'main', component: MainComponent, canActivate: [AuthorizationGuardService] },
  { path: 'loan', component: LoanComponent, canActivate: [AuthorizationGuardService] },
  { path: '**', redirectTo: 'main' }
];
export const routing = RouterModule.forRoot(appRoutes);
