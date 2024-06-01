package net.heidylazaro.loginsicenet.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.Namespace


//AccesoAlumno
@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class AccesoAlumnoEnvelope {
    @field:Element(name = "Body")
    var body: AccesoAlumnoBody? = null
}

class AccesoAlumnoBody {
    @field:Element(name = "accesoLoginResponse", required = false)
    @field:Namespace(reference = "http://tempuri.org/")
    var response: AccesoAlumnoResponse? = null
}

class AccesoAlumnoResponse {
    @field:Element(name = "accesoLoginResult", required = false)
    var result: String? = null
}


//DatosAlumno
@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class DatosAlumnoEnvelope {
    @field:Element(name = "Body")
    var body: DatosAlumnoBody? = null
}

class DatosAlumnoBody {
    @field:Element(name = "getAlumnoAcademicoWithLineamientoResponse", required = false)
    @field:Namespace(reference = "http://tempuri.org/")
    var response: DatosAlumnoResponse? = null
}

class DatosAlumnoResponse {
    @field:Element(name = "getAlumnoAcademicoWithLineamientoResult", required = false)
    var result: String? = null
}

// CargaAcademica
@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class CargaAcademicaEnvelope {
    @field:Element(name = "Body")
    var body: CargaAcademicaBody? = null
}

class CargaAcademicaBody {
    @field:Element(name = "getCargaAcademicaByAlumnoResponse", required = false)
    @field:Namespace(reference = "http://tempuri.org/")
    var response: CargaAcademicaResponse? = null
}

class CargaAcademicaResponse {
    @field:Element(name = "getCargaAcademicaByAlumnoResult", required = false)
    var result: String? = null
}

// Kardex
@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class KardexEnvelope {
    @field:Element(name = "Body")
    var body: KardexBody? = null
}

class KardexBody {
    @field:Element(name = "getAllKardexConPromedioByAlumnoResponse", required = false)
    @field:Namespace(reference = "http://tempuri.org/")
    var response: KardexResponse2? = null
}

class KardexResponse2 {
    @field:Element(name = "getAllKardexConPromedioByAlumnoResult", required = false)
    var result: String? = null
}

// CalificacionesUnidad
@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class CalificacionesUnidadEnvelope {
    @field:Element(name = "Body")
    var body: CalificacionesUnidadBody? = null
}

class CalificacionesUnidadBody {
    @field:Element(name = "getCalifUnidadesByAlumnoResponse", required = false)
    @field:Namespace(reference = "http://tempuri.org/")
    var response: CalificacionesUnidaResponse? = null
}

class CalificacionesUnidaResponse {
    @field:Element(name = "getCalifUnidadesByAlumnoResult", required = false)
    var result: String? = null
}

// CalificacionesFinales
@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class CalificacionesFinalesEnvelope {
    @field:Element(name = "Body")
    var body: CalificacionesFinalesBody? = null
}

class CalificacionesFinalesBody {
    @field:Element(name = "getAllCalifFinalByAlumnosResponse", required = false)
    @field:Namespace(reference = "http://tempuri.org/")
    var response: CalificacionesFinalesResponse? = null
}

class CalificacionesFinalesResponse {
    @field:Element(name = "getAllCalifFinalByAlumnosResult", required = false)
    var result: String? = null
}
