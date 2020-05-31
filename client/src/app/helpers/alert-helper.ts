import Swal, {SweetAlertResult} from 'sweetalert2';

export class AlertHelper {

    static success(message: string): Promise<SweetAlertResult> {
        return Swal.fire({
            icon: 'success',
            html: message,
        })
    }

    static error(message: string): Promise<SweetAlertResult> {
        return Swal.fire({
            icon: 'error',
            html: message,
        })
    }

    static warn(message: string): Promise<SweetAlertResult> {
        return Swal.fire({
            icon: 'warning',
            html: message,
        })
    }

    static ask(message: string): Promise<SweetAlertResult> {
        return Swal.fire({
            icon: 'warning',
            title: 'Are you sure?',
            html: message,
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Yes',
            showCancelButton: true,
            cancelButtonColor: '#d33',
            focusCancel: true
        });
    }

}
