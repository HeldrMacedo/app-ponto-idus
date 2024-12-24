import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideAnimations } from '@angular/platform-browser/animations';
import {provideHttpClient ,withFetch, withInterceptors } from '@angular/common/http';
import { provideToastr } from 'ngx-toastr'
import { meuhttpInterceptor } from './services/http-interceptor.service';

// Adiciona o provideHttpClient(withFetch()) para ter o httpClient injetado em toda a aplicação
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), 
    provideRouter(routes), 
    provideAnimations(),
    provideHttpClient(withFetch()),
    provideToastr(),
    provideAnimations(),
    provideHttpClient(withInterceptors([meuhttpInterceptor]))
  ]
};
